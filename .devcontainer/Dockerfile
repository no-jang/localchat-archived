# Use alpine base image from microsoft
FROM mcr.microsoft.com/vscode/devcontainers/base:0-alpine-3.15

# Configure Evironment variables
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk

# Upgrade alpine to v3.16
RUN sed -i -e 's/v3\.15/v3.16/g' /etc/apk/repositories
RUN apk update && apk upgrade

# Install packages
RUN apk add --no-cache \
    # Java
    openjdk17-jdk \

    # NodeJS
    nodejs-current \
    npm

# Install pnpm as package manager
RUN npm install -g pnpm