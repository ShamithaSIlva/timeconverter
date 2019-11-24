package com.example.formatter;

public enum MatchTimeTag
{
	PRE_MATCH("[PM]"), 
	FIRST_HALF("[H1]"),
	HALF_TIME("[HT]"),
	SECOND_HALF("[H2]"),
	FULL_TIME("[FT]");

    private String tag;

    MatchTimeTag(String tag) {
        this.tag = tag;
    }

    public String getTag() { 
        return tag;
    }
}
