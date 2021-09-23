#Git
 - ####git ssh配置
     - 生成ssh秘钥        
        ssh-keygen -t rsa -C "github邮箱" 
     - windows/linxu 秘钥路径       
        C:\Users\XXX\.ssh   
        cd ~/.ssh/ 
     - 配置github/gitlab/gitee ssh    
       id_rsa_pub 文件内容配置到github ssh配置区即可
     - 设置全局账号/用户名   
      git config --global user.name "Your Name"  
      git config --global user.email "email@example.com"    
     - 查看gitconfig配置信息   
      git config --list
 - #### 创建本地版本库
    - 新建空目录     
       mkdir dir
    - 初始化本地库    
       - 方式一 git init    
       - 方式二 (idea)VCS - > Import into Version Control -> Create Git Repository     
 - #### 提交代
   - 查看文件状态
       - git status 
   - 用命令git add告诉Git，把文件添加到stage：
       - git add . 提交所有修改的文件 (将工作区同步到stage区)
   - 用命令git commit告诉Git，把文件提交到本地仓库
       - git commit -m '提交说明'  (将stage区同步到本地库，并清空stage区。每次commit会生成一个快照id) 
 - #### 版本回退
    - 查看历史记录 
       - git log  查看提交历史记录(历史提交记录)
       - git log --pretty=oneline 简化提交历史记录信息
       - git reflog 查看命令历史(包含版本回退记录)
    - 版本回退
       - git reset --hard HEAD^ (上一个版本)
       - git reset --hard HEAD^^ (上上个版本)
       - git reset --hard 版本号 (回到对应版本号版本)   *提交之后的回滚*
 - #### 撤销修改 
   - git checkout -- file.txt (撤销工作区的最后一次修改)*添加之前的回滚*
   - git checkout 分支名(切换分支)
   - git reset HEAD <file> (撤销已经添加到stage区的修改，重新放回工作区)*添加之后的回滚*
 - #### 删除文件
   - git rm 文件名 (删除文件/也可直接物理删除)
   - git checkout -- file.txt(文件误删,从stage 区还原文件) 
 - #### 创建与合并分支
   - git checkout -b dev 创建dev分支
   - git branch 分支名 创建分支
   - git branch -v 查看本地分支
   - git branch -r 查看远程分支
   - git branch -a 查看本地和远程所有分支
   - git remote -v 查看远程库
   - git remote rm origin 删除远程库(解除本地和远程仓库的绑定关系)
   - git push origin --delete 分支名(删除远程分支)
   - git branch -d dev 删除本地分支
   - git branch -D dev 丢弃一个没有被合并过的分支，可以通过git branch -D <name>强行删除。
   - git remote add origin git@github.com:michaelliao/learngit.git 添加远程仓库关联    
   - git checkout dev 切换分支
   - git merge 分支名 合并分支     
   - ##### 创建并切换分支
     - git switch -c dev
     - git switch dev 切换分支
 - #### bug分支
   - 保存当前分支现场
   git stash (Git还提供了一个stash功能，可以把当前工作现场“储藏”起来，等以后恢复现场后继续工作：)
   - 切换到需要需要修复bug的分支，创建临时分支，修复bug 并合并
   - 切回stash 保存的分支现场
   - git stash list 查看缓存的工作现场
   - 恢复之前缓存的现场分支
      - 用git stash apply恢复，但是恢复后，stash内容并不删除，你需要用git stash drop来删除
         git stash apply stash@{0} 恢复现场
      - 用git stash pop，恢复的同时把stash内容也删了 
   - 当前分支也存在以上修复的bug
     git cherry-pick 修复bugcommit快照id (在master分支上修复的bug，想要合并到当前dev分支，可以用git cherry-pick <commit>命令，把bug提交的修改“复制”到当前分支，避免重复劳动。)

   -如果git pull提示no tracking information，则说明本地分支和远程分支的链接关系没有创建，用命令git branch --set-upstream-to <branch-name> origin/<branch-name>
   -  git log --graph --pretty=oneline --abbrev-commit (查看分支合并图)
   
 - #### 标签
   - git tag v1.0 设置标签
   - git tag 查看所有标签 
   - git tag v0.9 f52c633 给某次提交打标签
   - git tag -d v0.1 删除本地标签
   - git push origin :refs/tags/v0.9 删除远程标签
   - git push origin v1.0 推送某个标签的到远程
   - git push origin --tags 推送所有标签到远程
 - #### 设置别名
  - git config --global alias.co checkout (git co == git checkout)
  - git config --global alias.ci commit   (git ci == git commit)
  - git config --global alias.br branch   (git br == git branch)