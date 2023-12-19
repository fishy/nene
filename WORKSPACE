workspace(name = "nene")

android_sdk_repository(
    name = "androidsdk",
    api_level = 33,
)

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_jar")

RULES_JVM_EXTERNAL_TAG = "5.3"
RULES_JVM_EXTERNAL_SHA = "d31e369b854322ca5098ea12c69d7175ded971435e55c18dd9dd5f29cc5249ac"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    urls = [
        "https://github.com/bazelbuild/rules_jvm_external/releases/download/%s/rules_jvm_external-%s.tar.gz" % (RULES_JVM_EXTERNAL_TAG, RULES_JVM_EXTERNAL_TAG),
    ],
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "androidx.activity:activity:1.4.0",
        "androidx.appcompat:appcompat:1.3.1",
        "androidx.browser:browser:1.5.0",
        "androidx.core:core-ktx:1.8.0",
        "androidx.fragment:fragment-ktx:1.4.1",
        "androidx.preference:preference:1.2.0",
        "com.google.android.material:material:1.6.1",
    ],
    maven_install_json = "@nene//:maven_install.json",
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
    version_conflict_policy = "pinned",
)

load("@maven//:defs.bzl", "pinned_maven_install")

pinned_maven_install()

RULES_KOTLIN_TAG = "v1.9.0"
RULES_KOTLIN_SHA = "5766f1e599acf551aa56f49dab9ab9108269b03c557496c54acaf41f98e2b8d6"

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = RULES_KOTLIN_SHA,
    urls = [
        "https://github.com/bazelbuild/rules_kotlin/releases/download/%s/rules_kotlin-%s.tar.gz" % (RULES_KOTLIN_TAG, RULES_KOTLIN_TAG),
    ],
)

load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")

kotlin_repositories()

register_toolchains("//:kotlin_toolchain")

BUNDLE_TOOL_TAG = "1.15.5"
BUNDLE_TOOL_SHA = "0ebac88764e16b2154aa7506187917d169959338ad9d510e3174bcc96c9d0f40"

http_jar(
    name = "android_bundletool",
    sha256 = BUNDLE_TOOL_SHA,
    urls = [
        "https://github.com/google/bundletool/releases/download/%s/bundletool-all-%s.jar" % (BUNDLE_TOOL_TAG, BUNDLE_TOOL_TAG),
    ],
)

RULES_OPPIA_ANDROID_TAG = "v0.7"
RULES_OPPIA_ANDROID_SHA = "62e8d1f997a8a6b5ada27276e6c57a872b5eeaf3c89548d5a45981484e14ac96"

http_archive(
    name = "rules_oppia_android",
    sha256 = RULES_OPPIA_ANDROID_SHA,
    strip_prefix = "oppia-android-%s" % RULES_OPPIA_ANDROID_TAG[1:],
    urls = [
        "https://github.com/oppia/oppia-android/archive/%s.zip" % RULES_OPPIA_ANDROID_TAG,
    ],
)
