JAVA_HOME="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home"

cd src/main/java

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/Test.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/Test.nxj legomindstorms.nxt.Test
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/Test.nxj

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete