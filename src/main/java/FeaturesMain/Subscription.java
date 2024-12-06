package FeaturesMain;

public class Subscription {
	private String tier;
	private String price;
	private String discount;
	private String days;

	// Constructor
	public Subscription(String tier, String price, String discount, String days) {
		this.tier = tier;
		this.price = price;
		this.discount = discount;
		this.days = days;
	}

	// Getter and Setter for tier
	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	// Getter and Setter for price
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	// Getter and Setter for discount
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	// Getter and Setter for days
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	// toString method
	@Override
	public String toString() {
		return "Subscription{" + "tier='" + tier + '\'' + ", price='" + price + '\''
				+ ", discount on drinks and meals='" + discount + '\'' + ", days='" + days + '\'' + '}';
	}
}
