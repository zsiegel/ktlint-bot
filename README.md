# ktlint-bot
A repo for a [ktlint](https://ktlint.github.io/) bot that runs against the Github checks API. Livestreaming on [twitch](https://www.twitch.tv/zsiegel87/)

## Relevant Links
- [Github Checks API v3](https://developer.github.com/v3/checks/)
	- [Quick Start Guide](https://developer.github.com/apps/quickstart-guides/creating-ci-tests-with-the-checks-api/)
- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)
- [Vert.x](https://vertx.io/)
- [ktlint](https://ktlint.github.io/)

## Streams

### Stream 1 - July 15th 6pm CDT
- [Youtube replay](https://youtu.be/wIsFEgrNLsc?t=697)
- [Tagged Code](https://github.com/zsiegel/ktlint-bot/releases/tag/stream-01)

In this stream we completed the following.
- Setup a multi-module gradle project
	- Battled with a directory issue for awhile
- Organized our gradle build files
- Setup an example Vert.x http server to test our dependencies

### Stream 2 - July 16th 6pm CDT
- [Youtube replay](https://youtu.be/KlBeqbBnXaA)
- [Tagged Code](https://github.com/zsiegel/ktlint-bot/releases/tag/stream-02)

In this stream we completed the following.

- explored the vert.x event loop
- explored vert.x coroutine support

### Stream 3 - August 5th 5:15pm CDT
- [Youtube replay](https://youtu.be/_shULsQwNg8)
- [Tagged Code](https://github.com/zsiegel/ktlint-bot/releases/tag/stream-03)

In this stream we completed the following.

- created a coroutine extension function
- created a function to run ktlint on a kotlin file

## TODO
- [x] ktlint integration
- [ ] github checks API integration 