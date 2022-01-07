package com.fit.hcmus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class TaiKhoan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="trangthai")
	private String trangThai;
	
	@Column(name="vaitro")
	private String vaiTro;

	public TaiKhoan() {

	}
	
	public TaiKhoan(String username, String password, String trangThai, String vaiTro) {
		this.username = username;
		this.password = password;
		this.trangThai = trangThai;
		this.vaiTro = vaiTro;
	}

	public TaiKhoan(int id, String username, String password, String trangThai, String vaiTro) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.trangThai = trangThai;
		this.vaiTro = vaiTro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	@Override
	public String toString() {
		return "TaiKhoan [id=" + id + ", username=" + username + ", password=" + password + ", trangThai=" + trangThai
				+ ", vaiTro=" + vaiTro + "]";
	}
	
	
}
