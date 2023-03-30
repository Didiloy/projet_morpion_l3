#! /bin/bash
cd ../../
i=0
while [ $i -le $1 ]
do
    java -cp ./out/production/projet_morpion_l3 Main < ./src/scripts/input.txt
    ((i++))
done
