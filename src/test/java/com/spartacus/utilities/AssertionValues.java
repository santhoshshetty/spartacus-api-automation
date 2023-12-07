package com.spartacus.utilities;

import lombok.Data;

@Data
public class AssertionValues {

	String assertOnField;

	String expected;

	String actual;
}
