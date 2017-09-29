rm -rf $(find ./ -name target);mvn clean;git add -A;git commit -m "提交说明:$1";git push
