#!/bin/bash
set -o errexit
set -o nounset
set -o pipefail
set -e -x
export TERM=xterm
function main() {
#    set +x
    pushd src-repo
    pushd catalog-browser
    yarn install
    yarn build
    mv build ../../artifacts/
    popd
    popd
    exit 0
}
# INIT
main "$@"