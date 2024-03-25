//**********Answer 3**********
import com.atlassian.jira.issue.IssueConstant

//Get value from field of priority and summary
def priorityField = getFieldById(fieldChanged)
def priorityValue = priorityField.value as IssueConstant
def summaryField = getFieldById("summary")
def summaryValue = summaryField.getValue() as String

// If the chosen priority is "High" or "Highest"
if (priorityValue.name=="High"||priorityValue.name=="Highest") { 
    //Check if prefix exist to prevent duplicate prefix added
    if(!summaryValue.contains("URGENT:")){
        def updatedSummary = "URGENT: $summaryValue"
        summaryField.setFormValue(updatedSummary)
    }
} 
else {
    def updatedSummary = summaryValue.substring(summaryValue.indexOf(":") + 1)
    summaryField.setFormValue(updatedSummary.trim())
}
