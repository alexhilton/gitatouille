package com.hilton.gitatouille;

import java.util.ArrayList;
import java.util.List;

public class ProGit {
    public static final String CONTENT_DIR = "progit";
    
    private static List<ProGitChapter> sChapters;
    
    public static List<ProGitChapter> getChapters() {
        if (sChapters == null) {
            sChapters = generateChapters();
        }
        
        return sChapters;
    }
    
    private static List<ProGitChapter> generateChapters() {
        List<ProGitChapter> chapters = new ArrayList<ProGitChapter>();
        ProGitChapter chap = new ProGitChapter("1. 起步");
        ProGitSection sec = new ProGitSection("1. 起步", "ch1-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("1.1 - 关于版本控制", "ch1-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("1.2 - Git 的历史", "ch1-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("1.3 - Git 基础要点", "ch1-3.html");
        chap.addSection(sec);
        sec = new ProGitSection("1.4 - 安装 Git", "ch1-4.html");
        chap.addSection(sec);
        sec = new ProGitSection("1.5 - 初次运行 Git 前的配置", "ch1-5.html");
        chap.addSection(sec);
        sec = new ProGitSection("1.6 - 获取帮助", "ch1-6.html");
        chap.addSection(sec);
        sec = new ProGitSection("1.7 - 小结", "ch1-7.html");
        chap.addSection(sec);
        chapters.add(chap);
        chap = new ProGitChapter("2. Git 基础");
        sec = new ProGitSection("2. Git 基础", "ch2-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("2.1 - 取得项目的 Git 仓库", "ch2-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("2.2 - 记录每次更新到仓库", "ch2-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("2.3 - 查看提交历史", "ch2-3.html");
        chap.addSection(sec);
        sec = new ProGitSection("2.4 - 撤消操作", "ch2-4.html");
        chap.addSection(sec);
        sec = new ProGitSection("2.5 - 远程仓库的使用", "ch2-5.html");
        chap.addSection(sec);
        sec = new ProGitSection("2.6 - 打标签", "ch2-6.html");
        chap.addSection(sec);
        sec = new ProGitSection("2.7 - 技巧和窍门", "ch2-7.html");
        chap.addSection(sec);
        sec = new ProGitSection("2.8 - 小结", "ch2-8.html");
        chap.addSection(sec);
        chapters.add(chap);
        chap = new ProGitChapter("3. Git 分支");
        sec = new ProGitSection("3. Git 分支", "ch3-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("3.1 - 何谓分支", "ch3-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("3.2 - 基本的分支与合并", "ch3-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("3.3 - 分支管理", "ch3-3.html");
        chap.addSection(sec);
        sec = new ProGitSection("3.4 - 分支式工作流程", "ch3-4.html");
        chap.addSection(sec);
        sec = new ProGitSection("3.5 - 远程分支", "ch3-5.html");
        chap.addSection(sec);
        sec = new ProGitSection("3.6 - 衍合", "ch3-6.html");
        chap.addSection(sec);
        sec = new ProGitSection("3.7 - 小结", "ch3-7.html");
        chap.addSection(sec);
        chapters.add(chap);
        chap = new ProGitChapter("4. 服务器上的 Git");
        sec = new ProGitSection("4. 服务器上的 Git", "ch4-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.1 - 协议", "ch4-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.2 - 在服务器部署 Git", "ch4-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.3 - 生成 SSH 公钥", "ch4-3.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.4 - 架设服务器", "ch4-4.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.5 - 公共访问", "ch4-5.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.6 - 网页界面 GitWeb", "ch4-6.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.7 - 权限管理器 Gitosis", "ch4-7.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.8 - Git 进程", "ch4-8.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.9 - Git 托管服务", "ch4-9.html");
        chap.addSection(sec);
        sec = new ProGitSection("4.10 - 小节", "ch4-10.html");
        chap.addSection(sec);
        chapters.add(chap);
        chap = new ProGitChapter("5. 分布式 Git");
        sec = new ProGitSection("5. 分布式 Git", "ch5-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("5.1 - 分布式工作流程", "ch5-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("5.2 - 为项目作贡献", "ch5-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("5.3 - 项目的管理", "ch5-3.html");
        chap.addSection(sec);
        sec = new ProGitSection("5.4 - 小结", "ch5-4.html");
        chap.addSection(sec);
        chapters.add(chap);
        chap = new ProGitChapter("6. Git 工具");
        sec = new ProGitSection("6. Git 工具", "ch6-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("6.1 - 修订版本（Revision）选择", "ch6-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("6.2 - 交互式暂存", "ch6-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("6.3 - 储藏（Stashing）", "ch6-3.html");
        chap.addSection(sec);
        sec = new ProGitSection("6.4 - 重写历史", "ch6-4.html");
        chap.addSection(sec);
        sec = new ProGitSection("6.5 - 使用 Git 调试", "ch6-5.html");
        chap.addSection(sec);
        sec = new ProGitSection("6.6 - 子模块", "ch6-6.html");
        chap.addSection(sec);
        sec = new ProGitSection("6.7 - 子树合并", "ch6-7.html");
        chap.addSection(sec);
        sec = new ProGitSection("6.8 - 总结", "ch6-8.html");
        chap.addSection(sec);
        chapters.add(chap);
        chap = new ProGitChapter("7. 自定义 Git");
        sec = new ProGitSection("7. 自定义 Git", "ch7-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("7.1 - 配置 Git", "ch7-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("7.2 - Git属性", "ch7-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("7.3 - Git挂钩", "ch7-3.html");
        chap.addSection(sec);
        sec = new ProGitSection("7.4 - An Example Git-Enforced Policy", "ch7-4.html");
        chap.addSection(sec);
        sec = new ProGitSection("7.5 - Summary", "ch7-5.html");
        chap.addSection(sec);
        chapters.add(chap);
        chap = new ProGitChapter("8. Git 与其他系统");
        sec = new ProGitSection("8. Git 与其他系统", "ch8-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("8.1 - Git 与 Subversion", "ch8-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("8.2 - 迁移到 Git", "ch8-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("8.3 - 总结", "ch8-3.html");
        chap.addSection(sec);
        chapters.add(chap);
        chap = new ProGitChapter("9. Git 内部原理");
        sec = new ProGitSection("9. Git 内部原理", "ch9-0.html");
        chap.addSection(sec);
        sec = new ProGitSection("9.1 - 底层命令 (Plumbing) 和高层命令 (Porcelain)", "ch9-1.html");
        chap.addSection(sec);
        sec = new ProGitSection("9.2 - Git 对象", "ch9-2.html");
        chap.addSection(sec);
        sec = new ProGitSection("9.3 - Git References", "ch9-3.html");
        chap.addSection(sec);
        sec = new ProGitSection("9.4 - Packfiles", "ch9-4.html");
        chap.addSection(sec);
        sec = new ProGitSection("9.5 - The Refspec", "ch9-5.html");
        chap.addSection(sec);
        sec = new ProGitSection("9.6 - 传输协议", "ch9-6.html");
        chap.addSection(sec);
        sec = new ProGitSection("9.7 - 维护及数据恢复", "ch9-7.html");
        chap.addSection(sec);
        sec = new ProGitSection("9.8 - 总结", "ch9-8.html");
        chap.addSection(sec);
        chapters.add(chap);
        return chapters;
    }
}

class ProGitChapter {
    final String mName;
    private final List<ProGitSection> mSections;
    
    public ProGitChapter(final String name) {
        mName = name;
        mSections = new ArrayList<ProGitSection>();
    }
    
    public void addSection(final ProGitSection s) {
        mSections.add(s);
    }
    
    public ProGitSection getSection(int index) {
        return mSections.get(index);
    }
    
    public int getSectionCount() {
        return mSections.size();
    }
}

class ProGitSection {
    final String mName;
    private final String mContentLocation;
    
    public ProGitSection(String n, String cl) {
        this.mName = n;
        this.mContentLocation = cl;
    }
}
