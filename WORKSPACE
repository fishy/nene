workspace(name = "nene")

android_sdk_repository(
    name = "androidsdk",
    api_level = 31,
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

RULES_KOTLIN_TAG = "v1.8"

RULES_KOTLIN_SHA = "01293740a16e474669aba5b5a1fe3d368de5832442f164e4fbfc566815a8bc3a"

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = RULES_KOTLIN_SHA,
    urls = [
        "https://github.com/bazelbuild/rules_kotlin/releases/download/%s/rules_kotlin_release.tgz" % RULES_KOTLIN_TAG,
    ],
)

load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")

kotlin_repositories()

register_toolchains("//:kotlin_toolchain")

BUNDLE_TOOL_TAG = "1.15.1"

BUNDLE_TOOL_SHA = "aec9dc64fb25acc64eb668b45c0ec6a0ebba30db4a2e084b61b7af0a7380a0e1"

http_jar(
    name = "android_bundletool",
    sha256 = BUNDLE_TOOL_SHA,
    urls = [
        "https://github.com/google/bundletool/releases/download/%s/bundletool-all-%s.jar" % (BUNDLE_TOOL_TAG, BUNDLE_TOOL_TAG),
    ],
)

RULES_OPPIA_ANDROID_TAG = "v0.6"

RULES_OPPIA_ANDROID_SHA = "1d465ed74c32b98472401373305d57daca22de940ba5469acde4b0380c0cda4f"

http_archive(
    name = "rules_oppia_android",
    sha256 = RULES_OPPIA_ANDROID_SHA,
    strip_prefix = "oppia-android-%s" % RULES_OPPIA_ANDROID_TAG[1:],
    urls = [
        "https://github.com/oppia/oppia-android/archive/%s.zip" % RULES_OPPIA_ANDROID_TAG,
    ],
)
