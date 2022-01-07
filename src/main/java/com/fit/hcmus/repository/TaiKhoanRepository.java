package com.fit.hcmus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.hcmus.model.TaiKhoan;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
	List<TaiKhoan> findByUsername(String username);
}
