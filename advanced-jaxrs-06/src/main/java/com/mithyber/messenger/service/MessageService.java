package com.mithyber.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mithyber.messenger.database.DatabaseClass;
import com.mithyber.messenger.exception.DataNotFoundException;
import com.mithyber.messenger.model.Message;

public class MessageService {
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Message> getAllMessages() {
	return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
	return messages.values()
		.stream()
		.filter(m -> {
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(m.getCreated());
		    return calendar.get(Calendar.YEAR) == year;
		})
		.collect(Collectors.toList());
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
	if (start + size > messages.values()
		.size())
	    return new ArrayList<>();
	return new ArrayList<>(messages.values()).subList(start, start + size);
    }

    public Message getMessage(long id) {
	Message message = messages.get(id);
	if (message == null)
	    throw new DataNotFoundException("Message with id " + id + " not found");
	return message;
    }

    public Message addMessage(Message message) {
	message.setId(messages.size() + 1);
	messages.put(message.getId(), message);
	return message;
    }

    public Message updateMessage(Message message) {
	if (message.getId() <= 0)
	    return null;
	messages.put(message.getId(), message);
	return message;
    }

    public Message removeMessage(long id) {
	return messages.remove(id);
    }
}
