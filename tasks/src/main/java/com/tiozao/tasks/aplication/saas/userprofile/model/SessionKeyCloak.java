package com.tiozao.tasks.aplication.saas.userprofile.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class SessionKeyCloak implements Serializable {
  private String sub;

  private Boolean email_verified;

  private String clientHost;

  private String iss;

  private Boolean active;

  private String typ;

  private String preferred_username;

  private String clientAddress;

  private String client_id;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<String> aud;

  private String acr;

  private Realm_access realm_access;

  private String azp;

  private String scope;

  private Integer exp;

  private Integer iat;

  private String jti;

  private String username;

  public String getSub() {
    return this.sub;
  }

  public void setSub(String sub) {
    this.sub = sub;
  }

  public Boolean getEmail_verified() {
    return this.email_verified;
  }

  public void setEmail_verified(Boolean email_verified) {
    this.email_verified = email_verified;
  }

  public String getClientHost() {
    return this.clientHost;
  }

  public void setClientHost(String clientHost) {
    this.clientHost = clientHost;
  }

  public String getIss() {
    return this.iss;
  }

  public void setIss(String iss) {
    this.iss = iss;
  }

  public Boolean getActive() {
    return this.active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getTyp() {
    return this.typ;
  }

  public void setTyp(String typ) {
    this.typ = typ;
  }

  public String getPreferred_username() {
    return this.preferred_username;
  }

  public void setPreferred_username(String preferred_username) {
    this.preferred_username = preferred_username;
  }

  public String getClientAddress() {
    return this.clientAddress;
  }

  public void setClientAddress(String clientAddress) {
    this.clientAddress = clientAddress;
  }

  public String getClient_id() {
    return this.client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public List<String> getAud() {
    return this.aud;
  }

  public void setAud(List<String> aud) {
    this.aud = aud;
  }

  public String getAcr() {
    return this.acr;
  }

  public void setAcr(String acr) {
    this.acr = acr;
  }

  public Realm_access getRealm_access() {
    return this.realm_access;
  }

  public void setRealm_access(Realm_access realm_access) {
    this.realm_access = realm_access;
  }

  public String getAzp() {
    return this.azp;
  }

  public void setAzp(String azp) {
    this.azp = azp;
  }

  public String getScope() {
    return this.scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public Integer getExp() {
    return this.exp;
  }

  public void setExp(Integer exp) {
    this.exp = exp;
  }

  public Integer getIat() {
    return this.iat;
  }

  public void setIat(Integer iat) {
    this.iat = iat;
  }

  public String getJti() {
    return this.jti;
  }

  public void setJti(String jti) {
    this.jti = jti;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public static class Realm_access implements Serializable {
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> roles;

    public List<String> getRoles() {
      return this.roles;
    }

    public void setRoles(List<String> roles) {
      this.roles = roles;
    }
  }
}
