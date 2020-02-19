package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;

@WebServlet(value = {"/metrics", "/metrics/"}, name = "promServlet")
public class MetricsServlet2 extends MetricsServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println(CollectorRegistry.defaultRegistry);
    super.doGet(req, resp);
  }
}

