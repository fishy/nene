workspace(name = "nene")

android_sdk_repository(
    name = "androidsdk",
    api_level = 34,
)

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_jar")

BUNDLE_TOOL_TAG = "1.17.0"

http_jar(
    name = "android_bundletool",
    integrity = "sha256-VOvuHx3oNn2a0mtGcr+yl2sLEhQuFdaD/HuOJU/GzBs=",
    urls = [
        "https://github.com/google/bundletool/releases/download/%s/bundletool-all-%s.jar" % (BUNDLE_TOOL_TAG, BUNDLE_TOOL_TAG),
    ],
)

RULES_OPPIA_ANDROID_TAG = "v0.9"

http_archive(
    name = "rules_oppia_android",
    integrity = "sha256-Gdqg6aTQu8lH4bcGKzdCCsbeS6tj/6JijDg82iAbjCA=",
    strip_prefix = "oppia-android-%s" % RULES_OPPIA_ANDROID_TAG[1:],
    urls = [
        "https://github.com/oppia/oppia-android/archive/%s.zip" % RULES_OPPIA_ANDROID_TAG,
    ],
)
