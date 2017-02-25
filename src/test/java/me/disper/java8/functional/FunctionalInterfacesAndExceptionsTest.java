package me.disper.java8.functional;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class FunctionalInterfacesAndExceptionsTest
{

	@Test(expected = IOException.class)
	public void exceptionThrownInSignature() throws IOException
	{
		FunctionalInterfaceCheckedException lambda = () -> {
			throw new IOException("Because of reasons.");
		};

		lambda.process();
	}

	@Test()
	public void testWithTryCatch()
	{
		FunctionalInterfaceCheckedException lambda = () -> {
			throw new IOException("Because of reasons.");
		};

		try {
			lambda.process();
		} catch (IOException e) {
			// silence!
		}
	}
}
