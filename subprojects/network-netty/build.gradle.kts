dependencies {
    api(projects.subprojects.network)

    api("io.netty:netty5-handler:5.0.0.Alpha3")
    api("io.netty.contrib:netty-codec-extras:5.0.0.Alpha1")
    api("io.netty:netty5-transport-classes-epoll:5.0.0.Alpha3")
    api("io.netty:netty5-transport-classes-kqueue:5.0.0.Alpha3")

    runtimeOnly("io.netty:netty5-transport-native-epoll:5.0.0.Alpha3:linux-x86_64")
    runtimeOnly("io.netty:netty5-transport-native-kqueue:5.0.0.Alpha3:osx-x86_64")
}