#!/bin/sh

# Required for use with broken emacs on Mac OS X

echo "*** CALLING ant -emacs $* 2>&1 | tee .ant-log" 
echo "*** CALLING ant -emacs $* 2>&1 | tee .ant-log" > .ant-log
echo "ant -emacs $* 2>&1 | tee .ant-log" | /bin/sh
echo "*** FINISHED ant -emacs $* 2>&1 | tee .ant-log"
echo "*** FINISHED ant -emacs $* 2>&1 | tee .ant-log" >> .ant-log
