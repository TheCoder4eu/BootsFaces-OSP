# Guidelines for Contributing to BootsFaces

Would you like to contribute to BootsFaces? We'd love to have your contribution, and this are the guidelines we'd like you to follow so that we can work more effectively together:

 - [Issues and Bugs](#issue)
 - [Feature Requests](#feature)
 - [Submission Guidelines](#submit)
 - [BootsFaces project setup](#setup)
 - [Git Commit Guidelines](#commit)

## <a name="issue"></a> Issues and Bugs
If you find a bug in the source code or a mistake in the documentation, you can help us by submitting a Issue on [GitHub](https://github.com/TheCoder4eu/BootsFaces-OSP/issues). Even better, you can submit a Pull Request to our [BootsFaces project](https://github.com/TheCoder4eu/BootsFaces-OSP) or to our [Documentation project](https://github.com/TheCoder4eu/BootsFacesWeb/issues).

**Please see the Submission Guidelines below**.

## <a name="feature"></a> Feature Requests
You can request a new feature by submitting a Issue on [GitHub](https://github.com/TheCoder4eu/BootsFaces-OSP/issues). If you
would like to implement a new feature then consider what kind of change it is:

* **Small Changes** can be proposed without any discussion. Open up a Issue which clearly states that it is a feature request in the title. Explain your change in the description, and you can propose a Pull Request straight away.
* **Major Changes** that you wish to contribute to the project should be discussed first. Please open a ticket which clearly states that it is a feature request in the title and explain clearly what you want to achieve in the description, and the BootsFaces team will discuss with you what should be done in that ticket. You can then start working on a Pull Request.

## <a name="submit"></a> Submission Guidelines

### Submitting an Issue
Before you submit your issue search the archive, maybe your question was already answered.

If your issue appears to be a bug, and hasn't been reported, open a new issue.
Help us to maximize the effort we can spend fixing issues and adding new
features, by not reporting duplicate issues.  Providing the following information will increase the
chances of your issue being dealt with quickly:

* **Overview of the issue** - if an error is being thrown a stack trace helps
* **Motivation for or Use Case** - explain why this is a bug for you
* **BootsFaces Version(s)** - is it a regression?
* **Browsers and Operating System** - is this a problem with all browsers or only IE8?
* **Reproduce the error** - an unambiguous set of steps to reproduce the error. If you have a JavaScript error, maybe you can provide a live example with
  [JSFiddle](http://jsfiddle.net/)?
* **Related issues** - has a similar issue been reported before?
* **Suggest a Fix** - if you can't fix the bug yourself, perhaps you can point to what might be
  causing the problem (line of code or commit)

Click [here][1] to open a bug issue with a pre-filled template.

Issues opened without any of these info will be **closed** without any explanation.

## <a name="setup"></a> BootsFaces project setup
BootsFaces is developed as a [Gradle](http://gradle.org/) Project, however we support also Maven (see how to [build with Maven](https://github.com/TheCoder4eu/BootsFaces-OSP/blob/master/BUILD-MAVEN.txt) ).

Building BootsFaces with Gradle is very straightforward, the only prerequisite is having java installed (JDK 7 or later), there is no need to have Gradle installed.

Here are the most important steps.

### Fork the BootsFaces-OSP project

Go to the [BootsFaces-OSP project](https://github.com/TheCoder4eu/BootsFaces-OSP) and click on the "fork" button. You can then clone your own fork of the project, and start working on it.

[Please read the Github forking documentation for more information](https://help.github.com/articles/fork-a-repo)

### Set NPM to use the cloned project

In your cloned project, type:

```shell
./gradlew
```

on *NIX systems or

```shell
gradlew.bat
```

on Windows systems.

After few seconds you should see the "BUILD SUCCESSFUL" message and you will find the library in the subdirectory build/libs , ready to be included in your JSF Project.

## <a name="commit"></a> Git Commit Guidelines

We have rules over how our git commit messages must be formatted.

### <a name="commit-message-format"></a> Commit Message Format
Each commit message consists of a **header**, a **body** and a **footer**.

```
<header>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

Any line of the commit message cannot be longer 100 characters! This allows the message to be easier
to read on Github as well as in various git tools.

### Header
The Header contains a succinct description of the change:

* use the imperative, present tense: "change" not "changed" nor "changes"
* don't capitalize first letter
* no dot (.) at the end

###Body
If your change is simple, the Body is optional.

Just as in the Header, use the imperative, present tense: "change" not "changed" nor "changes".
The Body should include the motivation for the change and contrast this with previous behavior.

###Footer
The footer is the place to reference GitHub issues that this commit **Closes**.

You **must** use the [Github keywords](https://help.github.com/articles/closing-issues-via-commit-messages) for
automatically closing the issues referenced in your commit.

### Example
For example, here is a well-formatted commit message:

```
upgrade to jQuery 1.11.3

upgrade the Maven and Gradle builds to use the new version of jQuery,
see https://blog.jquery.com/2015/04/28/jquery-1-11-3-and-2-1-4-released-ios-fail-safe-edition/

Fix #1234
```

[1]:https://github.com/TheCoder4eu/BootsFaces-OSP/issues/new?body=*%20**Overview%20of%20the%20issue**%0A%0A%3C!--%20if%20an%20error%20is%20being%20thrown%20a%20stack%20trace%20helps%20--%3E%0A%0A*%20**Motivation%20for%20or%20Use%20Case**%20%0A%0A%3C!--%20explain%20why%20this%20is%20a%20bug%20for%20you%20--%3E%0A%0A*%20**BootsFaces%20Version(s)**%20%0A%0A%3C!--%20is%20it%20a%20regression%3F%20--%3E%0A%0A*%20**Browsers%20and%20Operating%20System**%20%0A%0A%3C!--%20is%20this%20a%20problem%20with%20all%20browsers%20or%20only%20IE8%3F%20--%3E%0A%0A*%20**Reproduce%20the%20error**%20%0A%0A%3C!--%20an%20unambiguous%20set%20of%20steps%20to%20reproduce%20the%20error.%20If%20you%20have%20a%20JavaScript%20error%2C%20maybe%20you%20can%20provide%20a%20live%20example%20with%0A%20%20%5BJSFiddle%5D(http%3A%2F%2Fjsfiddle.net%2F)%3F%20--%3E%0A%0A*%20**Related%20issues**%20%0A%0A%3C!--%20has%20a%20similar%20issue%20been%20reported%20before%3F%20--%3E%0A%0A*%20**Suggest%20a%20Fix**%20%0A%0A%3C!--%20if%20you%20can%27t%20fix%20the%20bug%20yourself%2C%20perhaps%20you%20can%20point%20to%20what%20might%20be%0A%20%20causing%20the%20problem%20(line%20of%20code%20or%20commit)%20--%3E
