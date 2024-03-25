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
