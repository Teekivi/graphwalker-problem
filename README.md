# graphwalker-problem
A minimal working/problematic example to demonstrate an issue with GraphWalker involving programmatically setting a
path generator and a stop condition in conjunction with a multi-model .json GraphWalker file.

## How to execute
For example in IntelliJ IDEA.
1. Clone the project.
2. Execute `mvn graphwalker:generate-sources`
3. Mark the target/generated-sources/graphwalker directory as a "Sources Root" if it isn't already.
4. Run src/main/java/com/example/runners/GraphWalkerProblemRunner.java.

## Information
The goal is to:
1. Model the System Under Test (SUT) as a single GraphWalker .json file containing multiple models.
2. Run tests on different parts of the SUT by applying different path generators and stop conditions on models. There
   remains the possibility to run tests on the full SUT.

The problem is that I haven't been able to programmatically set path generators and stop conditions on the models.
Initially I came up with a hackish solution that ran but unfortunately had no effect. The idea of the hackish solution
can be seen in [my post](https://groups.google.com/g/graphwalker/c/BD5IsDV2VdI/m/AR1YHpFaAwAJ) to GraphWalker Google
Groups.

The code currently used in this repo (in GraphWalkerProblemRunner.java) is adapted from
[a response](https://groups.google.com/g/graphwalker/c/BD5IsDV2VdI/m/87JIgDzbAwAJ) to my post. I think that this code is
an important step in the right direction. Unfortunately running it results in an error:

```
Exception in thread "main" org.graphwalker.core.condition.StopConditionException: Context missing a model
at org.graphwalker.core.condition.ReachedStopConditionBase.validate(ReachedStopConditionBase.java:55)
at org.graphwalker.core.condition.ReachedVertex.validate(ReachedVertex.java:63)
at org.graphwalker.core.condition.ReachedStopConditionBase.setContext(ReachedStopConditionBase.java:50)
at org.graphwalker.core.generator.PathGeneratorBase.setContext(PathGeneratorBase.java:50)
at org.graphwalker.core.machine.ExecutionContext.setPathGenerator(ExecutionContext.java:138)
at com.example.runners.GraphWalkerProblemRunner.main(GraphWalkerProblemRunner.java:18)
```

The goal of the code in GraphWalkerProblemRunner in this case is to get to the SecondView model as directly as possible
(without the possibility of entering FirstViewSubNode) and then execute SecondView for ten seconds.
