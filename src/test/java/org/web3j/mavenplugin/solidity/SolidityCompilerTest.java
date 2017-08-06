package org.web3j.mavenplugin.solidity;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SolidityCompilerTest {

    private SolidityCompiler solidityCompiler;

    @Before
    public void loadCompiler(){
        solidityCompiler = SolidityCompiler.getInstance();
    }

    @Test
    public void compileContract() throws Exception {
        byte[] source = Files.readAllBytes(Paths.get("src/test/resources/Greeter.sol"));

        CompilerResult compilerResult = solidityCompiler.compileSrc(source, SolidityCompiler.Options.ABI, SolidityCompiler.Options.BIN);

        assertFalse(compilerResult.isFailed());
        String preRelease = "Warning: This is a pre-release compiler version, please do not use it in production.";
        String errors = compilerResult.errors.replace(preRelease, "");
        assertTrue(compilerResult.errors, errors.isEmpty());
        assertFalse(compilerResult.output.isEmpty());

        assertTrue(compilerResult.output.contains("\"greeter\""));
    }

    @Test
    public void invalidContractVersion() throws Exception {
        byte[] source = Files.readAllBytes(Paths.get("src/test/resources/Greeter-invalid-version.sol"));

        CompilerResult compilerResult = solidityCompiler.compileSrc(source, SolidityCompiler.Options.ABI, SolidityCompiler.Options.BIN);

        assertTrue(compilerResult.isFailed());
        assertFalse(compilerResult.errors.isEmpty());
        assertTrue(compilerResult.output.isEmpty());
    }

    @Test
    public void invalidContractSyntax() throws Exception {
        byte[] source = Files.readAllBytes(Paths.get("src/test/resources/Greeter-invalid-syntax.sol"));

        CompilerResult compilerResult = solidityCompiler.compileSrc(source, SolidityCompiler.Options.ABI, SolidityCompiler.Options.BIN);

        assertTrue(compilerResult.isFailed());
        assertFalse(compilerResult.errors.isEmpty());
        assertTrue(compilerResult.output.isEmpty());
    }

}