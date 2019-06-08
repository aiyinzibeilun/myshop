本项目采用技术
springboot 2.1.4.RELEASE
springcloud Greenwich.SR1
docker 
docker-compose

以下基于docker构建
redis 5.0.5集群以及sentinel集群
nexus3 sonatype/nexus3
gitlab  twang2218/gitlab-ce-zh:10.6.6
registry

注：
    1.项目中的springconfig模块下的repo文件夹（与私服上的对应）用于上传所有的配置。
    2.所有项目resource文件目录下的配置文件只需要boostrap.yml即可。如果不使用分布式配置中心，可以采用application.yml的方式进行配置。
    
文件说明：
   Dockerfile主要是为了构建镜像
   docker-compose.yml可以同时启动多个镜像，多个镜像组成项目（可以在一个docker-compose.yml运行jar项目、数据库、redis等）
   .gitlab-ci.yml用来作持续集成
   .gitignore忽略不需要上传到gitlab(或者github)的文件，例如（target下面的编译文件等）
       
    
    




