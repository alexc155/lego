JAVA_HOME="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home"

cd src/main/java

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/robogator/Jaws.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/Jaws.nxj legomindstorms.nxt.robogator.Jaws
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/Jaws.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/robogator/Eyes.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/Eyes.nxj legomindstorms.nxt.robogator.Eyes
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/Eyes.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/robogator/Legs.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/Legs.nxj legomindstorms.nxt.robogator.Legs
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/Legs.nxj

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete