#!/bin/sh

set -e

bazel build --config=release //:app
