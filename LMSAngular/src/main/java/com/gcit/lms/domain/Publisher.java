package com.gcit.lms.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class Publisher {
 
	@Id
	private long publisherId;

	private String publisherName;

	private String publisherAddress;

	private String publisherPhone;

	 
	/**
	 * @return the id
	 */
	public long getId() {
		return publisherId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.publisherId = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return publisherName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.publisherName = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return publisherAddress;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.publisherAddress = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return publisherPhone;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.publisherPhone = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (publisherId ^ (publisherId >>> 32));
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
		Publisher other = (Publisher) obj;
		if (publisherId != other.publisherId)
			return false;
		return true;
	}
 
		 
}
