package com.mithyber.messenger.model;

// there is Link class in jax-rs, but it sometimes buggy the said
public class Link {
    private String link;
    private String rel;

    public Link() {
    }

    public Link(String link, String rel) {
	this.link = link;
	this.rel = rel;
    }

    public String getUrl() {
	return link;
    }

    public void setUrl(String url) {
	this.link = url;
    }

    public String getRel() {
	return rel;
    }

    public void setRel(String rel) {
	this.rel = rel;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((link == null) ? 0 : link.hashCode());
	result = prime * result + ((rel == null) ? 0 : rel.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Link other = (Link) obj;
	if (link == null) {
	    if (other.link != null)
		return false;
	} else if (!link.equals(other.link))
	    return false;
	if (rel == null) {
	    if (other.rel != null)
		return false;
	} else if (!rel.equals(other.rel))
	    return false;
	return true;
    }
}
