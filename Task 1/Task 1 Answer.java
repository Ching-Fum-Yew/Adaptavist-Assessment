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



//**********Answer 2**********
import com.atlassian.jira.component.ComponentAccessor

//Get some components
def loggedInUser = ComponentAccessor.jiraAuthenticationContext.loggedInUser
def issueService = ComponentAccessor.issueService

//Set variables
def issue = event.issue
def issueId = issue.id
def comment = event.getComment()
def commentBody = event.getComment().getBody()
def issueInputParameters = issueService.newIssueInputParameters()

//Update issue description with latest comment
issueInputParameters.setDescription(commentBody);

def updateValidationResult = issueService.validateUpdate(loggedInUser, issueId, issueInputParameters)

assert updateValidationResult.isValid() : updateValidationResult.errorCollection

def issueResult = issueService.update(loggedInUser, updateValidationResult)

assert issueResult.isValid() : issueResult.errorCollection



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