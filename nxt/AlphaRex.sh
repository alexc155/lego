JAVA_HOME="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home"

cd src/main/java

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/alpharex/Legs.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/Legs.nxj legomindstorms.nxt.alpharex.Legs
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/Legs.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/alpharex/Arms.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/Arms.nxj legomindstorms.nxt.alpharex.Arms
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/Arms.nxj

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete