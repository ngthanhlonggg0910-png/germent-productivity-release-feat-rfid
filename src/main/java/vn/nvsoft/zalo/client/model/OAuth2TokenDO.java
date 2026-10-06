package vn.nvsoft.zalo.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class OAuth2TokenDO extends OAuth2TokenErrorDO {

	private String access_token;

	private String expires_in;

	private String refresh_token;

	/**
	 * Default constructor.
	 */
	public OAuth2TokenDO() {
		super();
	}

	/** CSAClientManagerTest
	 * Indicate whether the response body token is valid or not.
	 * @return Valid if access token and refresh token are not empty or none values.
	 */
	@JsonIgnore
	public boolean isValidTokens() {
		return StringUtils.isNotEmpty(access_token)
				&& StringUtils.isNotEmpty(refresh_token);
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
            .append("error_name", getError_name())
            .append("error_reason", getError_reason())
            .append("error_description", getError_description())
				.append("access_token", access_token)
				.append("expires_in", expires_in)
				.append("refresh_token", refresh_token)
				.toString();
	}
}
