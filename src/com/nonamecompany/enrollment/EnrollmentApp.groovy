package com.nonamecompany.enrollment

import com.nonamecompany.enrollment.*
/**
 * This script will test the processCsvFile function.
 * It will also generate a file if one is not provided as an argument
 */

gen = new GenerateCsvFile()
proc = new ProcessCsvFile()
gen.generateCsvFile("enrollments.csv")
def csvFile = (args.size() != 0) ? args[0] : "enrollments.csv"
File file = new File(csvFile)
if(!file.exists()){
    println "File ${csvFile} does not exist"
    System.exit(1)
}

proc.processCsvFile(csvFile)
