package org.fpij.jitakyoei.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.view.forms.EntidadeForm;
import org.fpij.jitakyoei.view.gui.EntidadeCadastrarPanel;

public class EntidadeCadastrarView implements ViewComponent {

	private EntidadeCadastrarPanel gui;
	private AppFacade facade;
	private EntidadeForm entidadeForm;
	private MainAppView parent;
	
	
	public EntidadeCadastrarView(MainAppView parent) {
		this.parent = parent;
		gui = new EntidadeCadastrarPanel();
		gui.getCancelar().addActionListener(new CancelarActionHandler());
		gui.getCadastrarEntidade().addActionListener(new CadastrarActionHandler());
		entidadeForm = new EntidadeForm(gui.getEntidadePanel());
		gui.setVisible(true);
	}

	@Override
	public JPanel getGui() {
		return gui;
	}

	@Override
	public void registerFacade(AppFacade fac) {
		this.facade = fac;
	}
	
	/**
	 * Classe interna responsável por responder aos cliques no botão "Cadastrar".
	 * 
	 * @author Aécio
	 */
	public class CadastrarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				if (entidadeForm.getEntidade().getNome() == null || entidadeForm.getEntidade().getNome().isEmpty()) {
					JOptionPane.showMessageDialog(gui, "Erro: O nome da entidade não pode ser vazio!", 
												  "Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return;  
				}
	
				if (entidadeForm.getEntidade().getCnpj() == null || entidadeForm.getEntidade().getCnpj().isEmpty()) {
					JOptionPane.showMessageDialog(gui, "Erro: O CNPJ da entidade não pode ser vazio!", 
												  "Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return;  
				}
	
				if (entidadeForm.getEntidade().getTelefone1() == null || entidadeForm.getEntidade().getTelefone1().isEmpty()) {
					JOptionPane.showMessageDialog(gui, "Erro: O telefone da entidade não pode ser vazio!", 
												  "Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return;  
				}
				facade.createEntidade(entidadeForm.getEntidade());
				JOptionPane.showMessageDialog(gui, "Entidade cadastrada com sucesso!");
				parent.removeTabPanel(gui);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * Classe interna responsável por responder aos cliques no botão "Cancelar".
	 * 
	 * @author Aécio
	 */
	public class CancelarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.removeTabPanel(gui);
		}
	}
}
