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
    pushd purchase-api
    $BUILD_CMD
    $COPY_CMD
    popd
    popd
    exit 0
}
# INIT
main "$@"