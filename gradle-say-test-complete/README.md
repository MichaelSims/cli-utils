# What it is
Ever need to run some gradle tests in your IDE that may take a while? And you want to avoid your attention
wandering while you wait? What if gradle could tell you when the test was done? Well, now it can!

# Requirements
You need the "say" command, which is built-in on Mac OS X. If you're on Windows or Linux, you'll need to modify
the script to use a different command.

# Installing
Run `gradle-say-test-complete/install`, which copies a custom gradle init script to `~/.gradle/init.d`.

# Using
Run your test task as normal but pass `-PsayTestComplete` in addition to the other options. When the test is
done, `say "Test complete"` will be executed.
