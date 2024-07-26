# 使用官方的 Java 运行时镜像作为基础镜像
FROM openjdk:17-jdk-slim

# 将你的 jar 文件和启动脚本复制到 Docker 镜像中
COPY target/*.jar /home/app.jar
COPY start.sh /home/start.sh

WORKDIR /home
# 给启动脚本添加执行权限
RUN chmod a+x start.sh

# 暴露端口
EXPOSE 8000

# 设置启动命令
CMD ["./start.sh"]