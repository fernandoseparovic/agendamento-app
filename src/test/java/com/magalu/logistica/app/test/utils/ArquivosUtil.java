package com.magalu.logistica.app.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe que encapsula metodos utilitarios para trabalhar com arquivos.
 */
public class ArquivosUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArquivosUtil.class);

    /**
     * Carrega um arquivo no formato json e o converte para o tipoRetorno informado por parametro.
     *
     * @param <T> tipo generico esperado como retorno.
     * @param nomeArquivoOrigem - String que contem o nome do arquivo de origem.
     * @param tipoRetorno - Class do tipo esperado como retorno. 
     * @return Uma instancia de <T> caso o conversao a partir do arquivo json aconteca com sucesso. 
     * 
     */
    @SuppressWarnings("unchecked")
    public static <T> T carregaJson(final String nomeArquivoOrigem, final Class<T> tipoRetorno) {
    	InputStreamReader conteudoArquivoOrigem = null;

        try {
			conteudoArquivoOrigem = new InputStreamReader(new FileInputStream(nomeArquivoOrigem), StandardCharsets.UTF_8);
            final ObjectMapper objectMapper = new ObjectMapper();
            return (T) objectMapper.readValue(conteudoArquivoOrigem, Class.forName(tipoRetorno.getTypeName()));
		
        } catch (final Exception e) {
			LOGGER.error("Erro carregaJson: " + e);
			return null;
		
        } finally {
            try {
                if (conteudoArquivoOrigem != null) {
                    conteudoArquivoOrigem.close();
                }
            } catch (final IOException e) {
            	LOGGER.error("Erro carregaJson: " + e);
            }
        }
    }
}
