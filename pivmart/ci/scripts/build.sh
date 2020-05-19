#!/bin/bash
set -o errexit
set -o nounset
set -o pipefail
set -e -x
export TERM=xterm
function main() {
#    set +x
    pushd src-repo
    export SRC_ROOT=$PWD
    pushd pivmart
    echo "Build artifacts $BUILD_CMD -Dstubrunner.repository-root=stubs://file:/$SRC_ROOT/nexus/META-INF"
    STUBRUNNER_REPOSITORY_ROOT=stubs://file:/$SRC_ROOT/nexus/META-INF $BUILD_CMD
    $COPY_CMD
    popd
    popd
    exit 0
}
# INIT
main "$@"