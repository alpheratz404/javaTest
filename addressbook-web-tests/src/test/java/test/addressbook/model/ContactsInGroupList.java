package test.addressbook.model;


import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactsInGroupList extends ForwardingSet<ContactInGroupData> {
    private Set<ContactInGroupData> delegate;

    public ContactsInGroupList(Collection<ContactInGroupData> groups) {
        this.delegate = new HashSet<ContactInGroupData>(groups);
    }

    @Override
    protected Set<ContactInGroupData> delegate() {
        return delegate;
    }

    public ContactsInGroupList withAdded(ContactInGroupData group) {
        ContactsInGroupList groups = new ContactsInGroupList(this);
        groups.add(group);
        return groups;
    }

    public ContactsInGroupList without(ContactInGroupData group) {
        ContactsInGroupList groups = new ContactsInGroupList(this);
        groups.remove(group);
        return groups;
    }
}

