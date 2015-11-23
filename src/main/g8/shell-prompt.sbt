shellPrompt.in(ThisBuild) := (state => "[" + Project.extract(state).currentRef.project + "]> ")
