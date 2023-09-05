/* 2023 (C) Egon Willighagen <egon.willighagen@gmail.com>
 *
 * AGPL 3.0
 */
package io.github.egonw.jliebisch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.inchi.InChIGenerator;
import org.openscience.cdk.inchi.InChIGeneratorFactory;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IMolecularFormula;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.MolecularFormulaManipulator;

public class LMAbbrevToolTest {

    @Test
    public void testFAs() throws InvalidSmilesException {
        String cxsmiles = LMAbbrevTool.cxsmiles("FA 14:1");
        assertEquals("OC(=O)CC=CC[H] |Sg:n:3:x:ht,Sg:n:6:y:ht| x+y=11", cxsmiles);
        cxsmiles = LMAbbrevTool.cxsmiles("FA 10:1");
        assertEquals("OC(=O)CC=CC[H] |Sg:n:3:x:ht,Sg:n:6:y:ht| x+y=7", cxsmiles);
        SmilesParser parser = new SmilesParser(SilentChemObjectBuilder.getInstance());
        IAtomContainer container = parser.parseSmiles(cxsmiles);

        // please note that the molForm of CXSMILES does not reflect the extension in this format
        IMolecularFormula molForm = MolecularFormulaManipulator.getMolecularFormula(container);
        String mfString = MolecularFormulaManipulator.getString(molForm);
        assertEquals("C5H8O2", mfString); // instead of the actual C14H26O2
    }

    @Test
    public void testSpecificFAs() throws CDKException {
        String cxsmiles = LMAbbrevTool.cxsmiles("FA 10:0");
        SmilesParser parser = new SmilesParser(SilentChemObjectBuilder.getInstance());
        IAtomContainer container = parser.parseSmiles(cxsmiles);
        InChIGeneratorFactory factory = InChIGeneratorFactory.getInstance();
        InChIGenerator generator = factory.getInChIGenerator(container);
        String inchi = generator.getInchiKey();
        assertEquals("GHVNFZFCNZKVNT-UHFFFAOYSA-N", inchi);
    }

    @Test
    public void testCEs() throws InvalidSmilesException {
        String cxsmiles = LMAbbrevTool.cxsmiles("CE 8:0");
        assertEquals("C1[C@H](OC(=O)CCCCCCC)CC2=CC[C@@]3([H])[C@]4([H])CC[C@]([H])([C@]([H])(C)CCCC(C)C)[C@@]4(C)CC[C@]3([H])[C@@]2(C)C1", cxsmiles);
        SmilesParser parser = new SmilesParser(SilentChemObjectBuilder.getInstance());
        parser.parseSmiles(cxsmiles);
    }

    @Test
    public void testLPCs() throws InvalidSmilesException {
        String cxsmiles = LMAbbrevTool.cxsmiles("LPC 10:1");
        assertEquals("[C@](COP(=O)([O-])OCC[N+](C)(C)C)([H])(O)COC(=O)CC=CC[H] |Sg:n:19:x:ht,Sg:n:22:y:ht| x+y=7", cxsmiles);
        SmilesParser parser = new SmilesParser(SilentChemObjectBuilder.getInstance());
        parser.parseSmiles(cxsmiles);
    }

}