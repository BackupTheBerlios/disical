;;
;;
;; Helper funs for broken OSX emacs (use with bin/run_ant.sh)
;;

(setq err-decomp-buf 'null)
(defun error-decompilation (file)
  (interactive "f")
  (save-excursion
	 (if (not (equal err-decomp-buf 'null))
		  (kill-buffer err-decomp-buf))
	 (find-file-other-window file)
	 (setq err-decomp-buf (current-buffer))
	 (fill-region (point-min) (point-max))
	 (compilation-mode)
	 (goto-char (point-min))
	 (font-lock-fontify-buffer)
	 (add-hook 'kill-buffer-hook (lambda (&rest) (setq err-decomp-buf 'nil)) 'nil 't)
	 (set-buffer-modified-p 'nil)))
