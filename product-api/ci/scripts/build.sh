#!/bin/bash
set -o errexit
set -o nounset
set -o pipefail
set -e -x
export TERM=xterm
function main() {
    set +x
    pushd src-repo
    pushd product-api
    echo "Build artifacts"
    $BUILD_CMD
    $COPY_CMD
    popd
    popd
    exit 0
}
# INIT
main "$@"