# Flaky Tests Listener

One of the approaches how to evaluate flaky tests using TestNG framework.

The idea is to rerun test 3 times if it failed and:

    - it marked as a flaky using annotation (@Flaky(true) on test method)

    - three last results contains at least one 'failed'

Here implemented Retry Analyzer and Test Listener.
Provided just the idea, in practice you should use some storage to keep test results (DB/Excel/XML/textFile ...).
