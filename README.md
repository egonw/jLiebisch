# jLiebisch
Java library to convert Liebisch lipid notation to CXSMILES.

This repository will continue on some proof-of-principle code originating at the EpiLipidNET 'model organism'
hackathon in Muenchen in September 2023. These two code example [were created](https://egonw.github.io/cdk-cxsmiles/liebisch.html) there:

```groovy
cxsmiles = LMAbbrevTool.cxsmiles("CE 16:1")
mol1 = sp.parseSmiles(cxsmiles)
new DepictionGenerator()
  .withMolTitle()
  .depict(mol1)
  .writeTo("CE_16:1.svg");
```

and

```groovy
println LMAbbrevTool.cxsmiles("CE 8:0")
println LMAbbrevTool.cxsmiles("FA 14:1")
println LMAbbrevTool.cxsmiles("LPC 10:1")
```


