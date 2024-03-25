// **********Answer 1**********
//Number field for number of cat
def cat = 50
return cat

//Number field for number of dog
def dog = 20
return dog

//Calculate total
def dog = getCustomFieldValue("Number of dog") as Integer
def cat = getCustomFieldValue("Number of cat") as Integer
def total = dog + cat
return total
