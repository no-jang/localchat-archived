# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.236.0/containers/alpine/.devcontainer/base.Dockerfile

# [Choice] Alpine version: 3.15, 3.14, 3.13
ARG VARIANT="3.15"
FROM mcr.microsoft.com/vscode/devcontainers/base:0-alpine-${VARIANT}

# Install Java and NodeJS
RUN apk update 
RUN apk add --no-cache openjdk17
RUN apk add --no-cache npm

# Install pnpm via npm
RUN npm install -g pnpm