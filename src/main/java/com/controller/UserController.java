package com.controller;

import com.entities.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Model model, HttpSession session, String username, String password){
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        // 通过账号和密码查询用户
        User user = userService.findUser(u);
        if(user != null){
            // 将用户对象添加到Session
            session.setAttribute("user", user);
            // 跳转到主页面
            return "redirect:index";
        }
        model.addAttribute("msg", "账号或密码错误，请重新输入！");
        // 返回到登录页面
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();
        // 重定向到登录页面的跳转方法
        return "redirect:login";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("user/allUser")
    public String list(Model model) {
        List<User> list = userService.queryAllUser();
        model.addAttribute("list", list);
        return "allUser";
    }

    @RequestMapping(value = "user/search", method = RequestMethod.POST)
    public String search(Model model,User User){
        List<User> list = userService.queryUserByName(User);
        model.addAttribute("list",list);
        model.addAttribute("search_flag",true);
        return "allUser";
    }

    @RequestMapping("user/toAddUser")
    public String toAddUser() {
        return "addUser";
    }

    @RequestMapping("user/addUser")
    public String addUser(User User) {
        userService.addUser(User);
        return "redirect:/user/allUser";
    }

    @RequestMapping("user/del/{UserId}")
    public String deleteUser(@PathVariable("UserId") int id) {
        userService.deleteUserById(id);
        return "redirect:/user/allUser";
    }

    @RequestMapping("user/toUpdateUser")
    public String toUpdateUser(Model model, int id) {
        User User = userService.queryById(id);
        model.addAttribute("user",User);
        return "updateUser";
    }

    @RequestMapping("user/updateUser")
    public String updateUser(User User) {
        userService.updateUser(User);
        return "redirect:/user/allUser";
    }

    @RequestMapping("/toUpload")
    public String toUpload(){
        return "upload";
    }

    @RequestMapping(value="/upload")
    public String upload(@RequestParam("file")MultipartFile[] multipartFiles, Model model){//入参代表上传的文件

        //设置list存放数组
        List<String> fileNames = new ArrayList<String>();

        //0，判断是否为空
        if(multipartFiles!=null && multipartFiles.length>0)
        {
            for (MultipartFile multipartFile:multipartFiles)
            {
                if(multipartFile!=null && !multipartFile.isEmpty())
                {

                    /**
                     * 对文件名进行操作防止文件重名
                     */

                    //1，获取原始文件名
                    String originalFilename = multipartFile.getOriginalFilename();
                    //2,截取源文件的文件名前缀,不带后缀
                    String fileNamePrefix = originalFilename.substring(0,originalFilename.lastIndexOf("."));
                    //3,加工处理文件名，原文件加上时间戳
                    String newFileNamePrefix  = fileNamePrefix + System.currentTimeMillis();
                    //4,得到新文件名
                    String newFileName = newFileNamePrefix + originalFilename.substring(originalFilename.lastIndexOf("."));


                    //5,构建文件对象
                    File file = new File("G:/upload_file/" + newFileName);
                    //6,执行上传操作
                    try {
                        multipartFile.transferTo(file);
                        //上传成功，把 文件名 存入list里面
                        fileNames.add(newFileName);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        //list传送到前端
        model.addAttribute("fileNames",fileNames);

        return "upload";
    }

    private static final String FILE_1 = "layui-v2.4.5.zip";
    private static final String FILE_2 ="G:/layui-v2.4.5.zip";

    @RequestMapping(value="/toDownload", method=RequestMethod.GET)
    public String toDownload()
    {
        return "download";
    }


    @RequestMapping(value="/download/{type}", method=RequestMethod.GET)
    public void download(HttpServletResponse response, @PathVariable("type")  String type) throws IOException
    {
        File file = null;

        if( type.equalsIgnoreCase("internal") )
        {
            //内部地址
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            file = new File( classLoader.getResource(FILE_1).getFile() );
        }else {
            //外部地址
            file = new File(FILE_2);
        }


        //文件是否存在
        if(!file.exists())
        {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;

        }


        //获取文件类型，并输出文件类型
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType == null)
        {
            System.out.println("mimeType is not detectable.");
            mimeType = "application/octet-stream";
        }
        System.out.println("mimeType:" + mimeType);

        //输出类型
        response.setContentType(mimeType);
        //mime协议:默认文件名
        response.setHeader("Content-Disposition", String.format("inline: filename=\""+file.getName()+"\""));
        //mime协议：以附件形式下载
        response.setHeader("Content-Disposition", String.format("attachment: filename=\"%s\"", file.getName()));
        //文件大小
        response.setContentLength((int) file.length());

        //读入文件,然后再输出
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        //拷贝，下载
        FileCopyUtils.copy(inputStream, response.getOutputStream());

    }
}
