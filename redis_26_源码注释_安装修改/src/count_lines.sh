#!/bin/bash
#/home/liunx2014/Linux_2014/soft/annotated_redis_source_2.6-unstable/src
#count_lines.sh
extens=(".c" ".cpp" ".h" ".hpp")

filesCount=0
linesCount=0
function funCount()
{
    for file in ` ls $1 `
    do
        if [ -d $1"/"$file ];then
            funCount $1"/"$file
        else
            fileName=$1"/"$file
           
            EXTENSION="."${fileName##*.}
            echo "fileName = $fileName  Extension = $EXTENSION"

            if [[ "${extens[@]/$EXTENSION/}" != "${extens[@]}" ]];then
                declare -i fileLines
                fileLines=`sed -n '$=' $fileName`
                echo $fileName" : "$fileLines
                let linesCount=$linesCount+$fileLines
                let filesCount=$filesCount+1
            fi
        fi
    done
}

if [ $# -gt 0 ];then
    for m_dir in $@
    do
        funCount $m_dir
    done
else
    funCount "."
fi
echo "filesCount = $filesCount"
echo "linesCount = $linesCount"







