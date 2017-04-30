package com.mithyber.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.mithyber.messenger.model.Message;
import com.mithyber.messenger.model.Profile;

public class DatabaseClass {
    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    static {
	messages.put(1L, new Message(1, "Freedom is slavery", "BigBrother"));
	messages.put(2L, new Message(2, "Ignorance is strength", "BigBrother"));
    }

    static {
	profiles.put("BigBrother", new Profile(1, "BigBrother", "Big", "Brother"));
    }

    public static Map<Long, Message> getMessages() {
	return messages;
    }

    public static Map<String, Profile> getProfiles() {
	return profiles;
    }
}
