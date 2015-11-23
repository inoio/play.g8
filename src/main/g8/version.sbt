// set a global version for the release plugin
// the release plugin will control this setting
// when doing a release via `sbt release`
version in ThisBuild := "$version$"
